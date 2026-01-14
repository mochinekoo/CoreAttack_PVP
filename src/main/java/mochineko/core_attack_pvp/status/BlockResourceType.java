package mochineko.core_attack_pvp.status;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum BlockResourceType {
    //鉱石系
    COAL_ORE(Material.COAL_ORE, Material.COAL, 10),
    IRON_ORE(Material.IRON_ORE, Material.IRON_ORE, 10),
    GOLD_ORE(Material.GOLD_ORE, Material.GOLD_ORE, 10),
    DIAMOND_ORE(Material.DIAMOND_ORE, Material.DIAMOND, 10),
    EMERALD_ORE(Material.EMERALD_ORE, Material.EMERALD, 10),
    REDSTONE_ORE(Material.REDSTONE_ORE, Material.REDSTONE, 10),
    LAPIS_ORE(Material.LAPIS_ORE, Material.LAPIS_LAZULI, 10),

    //その他資材
    OAK_LOG(Material.OAK_LOG, Material.OAK_LOG, 10),
    GRAVEL(Arrays.asList(Material.GRAVEL), Arrays.asList(Material.BONE, Material.BOW), 10),
    MELON(Material.MELON, Material.MELON_SLICE, 10);

    private final List<Material> breakMaterial;
    private final List<Material> obtainMaterial;
    private final int respawnTime;

    BlockResourceType(Material breakMaterial, Material getMaterial, int respawnTime) {
        this.breakMaterial = Collections.singletonList(breakMaterial);
        this.obtainMaterial = Collections.singletonList(getMaterial);
        this.respawnTime = respawnTime;
    }

    BlockResourceType(List<Material> breakMaterial, List<Material> getMaterial, int respawnTime) {
        this.breakMaterial = breakMaterial;
        this.obtainMaterial = getMaterial;
        this.respawnTime = respawnTime;
    }

    public List<Material> getBreakMaterial() {
        return breakMaterial;
    }

    public List<Material> getObtainMaterial() {
        return obtainMaterial;
    }

    public Material getRandomObtainMaterial() {
        ArrayList<Material> list = new ArrayList<>(obtainMaterial);
        Collections.shuffle(list);
        return list.get(0);
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public static boolean isBlockResourceType(Material material) {
        return Arrays.stream(values())
                .anyMatch(type -> type.getBreakMaterial().contains(material));
    }

    public static BlockResourceType getBlockResourceType(Material material) {
        return Arrays.stream(values())
                .filter(type -> type.getBreakMaterial().contains(material))
                .findFirst()
                .orElse(null);
    }
}
