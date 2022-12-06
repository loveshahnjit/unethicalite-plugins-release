version = "0.0.4"

project.extra["PluginName"] = "Unethical Bank"
project.extra["PluginDescription"] = "Banks easily"
project.extra["GithubUrl"] = "https://github.com/4vvshah/unethicalite-plugins-release"
project.extra["GithubUserName"] = "4vvshah"
project.extra["GithubRepoName"] = "unethicalite-plugins-release"


tasks {
    jar {
        manifest {
            attributes(mapOf(
                "Plugin-Version" to project.version,
                "Plugin-Id" to nameToId(project.extra["PluginName"] as String),
                "Plugin-Provider" to project.extra["PluginProvider"],
                "Plugin-Description" to project.extra["PluginDescription"],
                "Plugin-License" to project.extra["PluginLicense"],
            ))
        }
    }
}
