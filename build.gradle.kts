plugins {
  id(ScriptPlugins.infrastructure)
}

buildscript {
  repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath (BuildPlugins.androidGradlePlugin)
    classpath (BuildPlugins.mavenGradlePlugin)
    classpath (BuildPlugins.kotlinGradlePlugin)
  }
}

allprojects {
  repositories {
    google()
    jcenter()
    mavenCentral()
    maven {
      url = uri("https://jitpack.io")
    }
  }
}
