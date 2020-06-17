plugins {
    java
    kotlin("jvm") version "1.3.72"
}

group = "ru.alfomine"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

val fatJar = task("fatJar", type = Jar::class) {
    manifest {
        attributes["Main-Class"] = "ru.alfomine.afmse.MainKt"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
}

val runApp = task("runApp", type = Exec::class) {
    if (tasks.jar.isPresent) {
        commandLine("java", "-jar", tasks.jar.get().archiveFile.get().toString())
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    build {
        dependsOn(fatJar)
        finalizedBy(runApp)
    }
}