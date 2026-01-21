pipeline {
    agent any

    tools {
        maven 'MAVEN_3_9_11'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
    }
}

