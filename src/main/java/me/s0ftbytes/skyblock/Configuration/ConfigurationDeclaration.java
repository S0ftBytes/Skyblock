package me.s0ftbytes.skyblock.Configuration;

public enum ConfigurationDeclaration {
    STATS("stats"),
    ENTITIES("entities"),
    SKILLS("skills"),
    PLAYER_DATA("player-data");

    private ConfigurationFile file;

    ConfigurationDeclaration(String fileName) {
        this.file = new ConfigurationFile(fileName);
    }

    public ConfigurationFile getFile() {
        return file;
    }
}
