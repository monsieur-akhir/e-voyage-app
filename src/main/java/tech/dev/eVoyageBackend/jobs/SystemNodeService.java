package tech.dev.eVoyageBackend.jobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import tech.dev.eVoyageBackend.factory.RedisEnum;
import tech.dev.eVoyageBackend.factory.RedisFactory;
import tech.dev.eVoyageBackend.factory.RedisValue;
import tech.dev.eVoyageBackend.jobs.Node;
import tech.dev.eVoyageBackend.jobs.NodeDto;
import tech.dev.eVoyageBackend.utils.ParamConfig;
import tech.dev.eVoyageBackend.utils.Utilities;

@Component
public class SystemNodeService {

	@Autowired
	private RedisFactory redisFactory;
	@Autowired
	private Node nodeObject;

	@Autowired
	private ParamConfig paramConfig;

	private RedisValue<tech.dev.eVoyageBackend.jobs.NodeDto> redisValue;

	private List<String> list_of_ip;

	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@PostConstruct
	public void init() {
		// Initialisation de la liste des IP après l'injection des dépendances
		list_of_ip = Arrays.asList(paramConfig.getRedisHost());
	}

	/**
	 * create (if not exist) a node in redis server update lastPing value of a node
	 *
	 * @param node
	 */
	public void pingNode(tech.dev.eVoyageBackend.jobs.NodeDto node, boolean isDelay) {
		redisValue = redisFactory.get(RedisEnum.NODE.name());
		if (redisValue == null) {
			return;
		}
		final tech.dev.eVoyageBackend.jobs.NodeDto nodeDto = (tech.dev.eVoyageBackend.jobs.NodeDto) redisValue.get(node.getName());
		if (nodeDto == null) {
			this.createNode(node, isDelay);
			return;
		}
		nodeDto.setLastPing(simpleDateFormat.format(new Date()));
		updateNode(nodeDto, isDelay);
	}

	private void createNode(tech.dev.eVoyageBackend.jobs.NodeDto node, boolean isDelay) {
		if (list_of_ip.stream().anyMatch(s -> node.getName().split("-")[2].contains(s))) {
			node.setLeader(Boolean.FALSE);
			node.setCreatedAt(simpleDateFormat.format(new Date()));
			redisValue.save(node.getName(), node, isDelay);
		}
	}

	public void updateNode(tech.dev.eVoyageBackend.jobs.NodeDto node, boolean isDelay) {
		if (list_of_ip.stream().anyMatch(s -> node.getName().split("-")[2].contains(s))) {
			redisValue.save(node.getName(), node, isDelay);
		}
	}

	public void electLeader(tech.dev.eVoyageBackend.jobs.NodeDto nodeDto, boolean isDelay) {
		System.out.println("electLeader en cours");

		System.out.println("noeud executant : " + new Gson().toJson(nodeDto));
		redisValue = redisFactory.get(RedisEnum.NODE.name());
		if (redisValue == null) {
			return;
		}
		String pattern = this.getNodePattern(nodeDto);
		List<tech.dev.eVoyageBackend.jobs.NodeDto> activeNodes = nodeObject.getClusterNodes(pattern);
		tech.dev.eVoyageBackend.jobs.NodeDto leader = findLeader(activeNodes);

		if (leader != null && !leader.isDown()) {
			activeNodes.forEach(node -> {
				if (list_of_ip.stream().anyMatch(s -> node.getName().split("-")[2].contains(s))) {
					node.setLeader(false);
					if (leader.getName().equals(node.getName())) {
						node.setLeader(true);
					}
					this.redisValue.save(node.getName(), node, isDelay);
					System.out.println("noeud updated : " + new Gson().toJson(node));
				} else {
					System.out.println("don't save noeud : " + node.getName().split("-")[2]);
				}
			});
		} else {
			activeNodes.forEach(node -> {
				if (list_of_ip.stream().anyMatch(s -> node.getName().split("-")[2].contains(s))) {
					node.setLeader(false);
					if (node.getName().equals(nodeDto.getName())) {
						node.setLeader(true);
					}
					this.redisValue.save(node.getName(), node, isDelay);
					System.out.println("noeud updated : " + new Gson().toJson(node));
				} else {
					System.out.println("don't save noeud : " + node.getName().split("-")[2]);
				}
			});
		}

		List<tech.dev.eVoyageBackend.jobs.NodeDto> nodesSaved = nodeObject.getClusterNodes(pattern);
		tech.dev.eVoyageBackend.jobs.NodeDto nodeLeader = nodesSaved.stream().filter(n -> n.isLeader()).findFirst().orElse(null);
		if (Objects.nonNull(nodeLeader)) {
			System.out.println("noeud leader : " + new Gson().toJson(nodeLeader));
		}
	}

	public void shutdownNode(tech.dev.eVoyageBackend.jobs.NodeDto nodeDto, boolean isDelay) {
		System.out.println("shutdown inactives nodes process");
		System.out.println("noeud executant : " + new Gson().toJson(nodeDto));
		redisValue = redisFactory.get(RedisEnum.NODE.name());
		if (redisValue == null) {
			return;
		}
		String pattern = this.getNodePattern(nodeDto);
		List<tech.dev.eVoyageBackend.jobs.NodeDto> activeNodes = nodeObject.getClusterNodes(pattern);

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		final Date currentDate = new Date();
		if (Utilities.isNotEmpty(activeNodes)) {
			activeNodes.forEach(node -> {
				try {
					if (node.getLastPing() != null) {
						Date lastPingDate = format.parse(node.getLastPing());
						long diff = currentDate.getTime() - lastPingDate.getTime();
						long diffMinutes = diff / (60 * 1000) % 60;
						System.out.println("diffMinutes (temps d'inactivite): " + diffMinutes);
						if (diffMinutes >= 10 && !Objects.equals(nodeDto.getName(), node.getName())) {
							if (list_of_ip.stream().anyMatch(s -> node.getName().split("-")[2].contains(s))) {
								node.setDown(true);
								this.redisValue.save(node.getName(), node, isDelay);
								System.out.println("noeud down : " + new Gson().toJson(node));
							} else {
								System.out.println("don't save noeud : " + node.getName().split("-")[2]);
							}
						} else {
							if (list_of_ip.stream().anyMatch(s -> node.getName().split("-")[2].contains(s))) {
								node.setDown(false);
								this.redisValue.save(node.getName(), node, isDelay);
								System.out.println("noeud actif : " + new Gson().toJson(node));
							} else {
								System.out.println("don't save noeud : " + node.getName().split("-")[2]);
							}
						}
					} else {
						if (list_of_ip.stream().anyMatch(s -> node.getName().split("-")[2].contains(s))) {
							node.setDown(true);
							this.redisValue.save(node.getName(), node, isDelay);
							System.out.println("noeud down : " + new Gson().toJson(node));
						} else {
							System.out.println("don't save noeud : " + node.getName().split("-")[2]);
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			});
		}

		System.out.println("end shutdown process");
	}

	public tech.dev.eVoyageBackend.jobs.NodeDto getNodeDto(tech.dev.eVoyageBackend.jobs.NodeDto nodeDto) {
		redisValue = redisFactory.get(RedisEnum.NODE.name());
		if (redisValue == null) {
			return null;
		}
		return (tech.dev.eVoyageBackend.jobs.NodeDto) redisValue.get(nodeDto.getName());
	}

	public boolean isLeader(tech.dev.eVoyageBackend.jobs.NodeDto nodeDto) {
		return nodeDto.isLeader();
	}

	private String getNodePattern(tech.dev.eVoyageBackend.jobs.NodeDto nodeDto) {
		return nodeDto.getName().substring(0, nodeDto.getName().lastIndexOf("-"));
	}

	private tech.dev.eVoyageBackend.jobs.NodeDto findLeader(List<tech.dev.eVoyageBackend.jobs.NodeDto> nodes) {
		return nodes.stream().filter(NodeDto::isLeader).findFirst().orElse(null);
	}
}
