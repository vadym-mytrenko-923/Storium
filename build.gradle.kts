import java.util.Locale

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidMultiplatformLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.detekt) apply false
}

val execHookTask = tasks.register("markGitHookExecutable", Exec::class) {
    description = "Marks Git Hooks Executable"
    group = "Setup"
    workingDir = rootDir
    commandLine = listOf("chmod")
    setArgs(listOf("-R", "+x", ".git/hooks/"))
    notCompatibleWithConfigurationCache("Executes commands directly on CLI.")
    onlyIf { isLinuxOrMacOS() }
}

tasks.register("installGitHooks", Copy::class) {
    description = "Installs Git Hooks"
    group = "Setup"

    if (isLinuxOrMacOS()) {
        from("$rootDir/hooks/pre-push-unix") {
            rename("pre-push-unix", "pre-push")
        }
    } else {
        from("$rootDir/hooks/pre-push-windows") {
            rename("pre-push-windows", "pre-push")
        }
    }
    into("$rootDir/.git/hooks")

    finalizedBy(execHookTask)
}

tasks.register("deleteGitHooks", Delete::class) {
    description = "Deletes Git Hooks"
    group = "Setup"
    delete("$rootDir/.git/hooks")
}

fun isLinuxOrMacOS(): Boolean {
    val osName = System.getProperty("os.name").lowercase(Locale.ROOT)
    return osName.contains("linux") || osName.contains("mac os") || osName.contains("macos")
}