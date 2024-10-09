pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Merge to Dockerization') {
            steps {
                script {
                    sh """
                        git checkout dockerization
                        git merge origin/dev
                        git push origin dockerization
                    """
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}