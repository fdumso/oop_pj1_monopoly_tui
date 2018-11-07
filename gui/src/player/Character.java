package player;

public enum Character {
    BROOK, CHOPPER, LUFFY, NAMI, SANJI, ZORO, FRANKY, SOGEKING, ROBIN;

    @Override
    public String toString() {
        switch (this) {
            case BROOK: return "brook";
            case CHOPPER: return "chopper";
            case LUFFY: return "luffy";
            case NAMI: return "nami";
            case SANJI: return "sanji";
            case ZORO: return "zoro";
            case FRANKY: return "franky";
            case SOGEKING: return "sogeking";
            case ROBIN: return "robin";
            default: return "";
        }
    }

}
