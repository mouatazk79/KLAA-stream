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

        stage('Deploy to K8s') {
            steps {
                // Add your Kubernetes deployment steps here
                // For example:
                // sh 'kubectl apply -f k8s-deployment.yaml'
            }
        }

        stage('Merge to Main') {
            steps {
                script {
                    sh """
                        git checkout main
                        git merge origin/k8s
                        git push origin main
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