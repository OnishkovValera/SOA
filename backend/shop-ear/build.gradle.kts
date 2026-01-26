plugins {
    id("ear")
}

group = "org.onishkovValery"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    deploy(project(":shop-ejb", configuration = "archives"))
    deploy(project(":shop-web", configuration = "archives"))
    
    // Shared libraries to be placed in EAR/lib
    earlib(project(":shared-libs"))
    earlib("org.jetbrains.kotlin:kotlin-reflect:2.1.0")
}

tasks.ear {
    archiveFileName.set("shop-app.ear")
    deploymentDescriptor {
        webModule("shop-web.war", "/shop")
    }
}
