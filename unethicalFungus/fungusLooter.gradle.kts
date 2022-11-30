version = "1.1.5"

project.extra["PluginName"] = "unethicalFungus"
project.extra["PluginDescription"] = "Loots fungus, uses ardy cape to restore prayer, banks at zanaris."

tasks {
    jar {
        manifest {
            attributes(mapOf(
                    "Plugin-Version" to project.version,
                    "Plugin-Id" to nameToId(project.extra["PluginName"] as String),
                    "Plugin-Provider" to project.extra["PluginProvider"],
                    "Plugin-Description" to project.extra["PluginDescription"],
                    "Plugin-Dependencies" to
                            arrayOf(
                                    nameToId("iUtils"),
                                    "chinbreakhandler-plugin"
                            ).joinToString()
                    "Plugin-License" to project.extra["PluginLicense"]
            ))
        }
    }
}
