package mochineko.core_attack_pvp.status;

import mochineko.core_attack_pvp.json.BlockGuardJson;
import mochineko.core_attack_pvp.library.DeserializedJson;

public enum FileType {

    CONFIG("block_guard.json", BlockGuardJson.class);

    private final String name;
    private final Class<? extends DeserializedJson> deserialized_class;

    FileType(String name, Class<? extends DeserializedJson> deserialized_class) {
        this.name = name;
        this.deserialized_class = deserialized_class;
    }

    public String getName() {
        return name;
    }

    public Class<? extends DeserializedJson> getDeserializedClass() {
        return deserialized_class;
    }

}
