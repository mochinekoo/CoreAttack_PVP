package mochineko.core_attack_pvp.library;

public abstract class KitBase {

    private String kit_name;

    public KitBase(String kit_name) {
        this.kit_name = kit_name;
    }

    public String getName() {
        return kit_name;
    }
}
