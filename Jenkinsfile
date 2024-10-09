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

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("your-image-name:${env.BUILD_NUMBER}")
                }
            }
        }

        stage('Merge to K8s') {
            steps {
                script {
                    sh """
                        git checkout k8s
                        git merge origin/dockerization
                        git push origin k8s
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