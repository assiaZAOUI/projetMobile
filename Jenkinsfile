pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9'
    }
    
    environment {
        APP_NAME = "tp-spring-boot"
        VERSION = "${BUILD_NUMBER}"
        DOCKER_IMAGE = "${APP_NAME}:${VERSION}"
    }
    
    stages {
        stage('📥 Checkout') {
            steps {
                echo 'Récupération du code source...'
                checkout scm
            }
        }
        
        stage('🔨 Build Maven') {
            steps {
                echo 'Compilation du projet Spring Boot...'
                bat 'mvn clean compile'
            }
        }
        
        stage('🧪 Tests Unitaires') {
            steps {
                echo 'Exécution des tests...'
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('📦 Package') {
            steps {
                echo 'Création du JAR...'
                bat 'mvn package -DskipTests'
            }
        }
        
        stage('🐳 Build Docker Image') {
            steps {
                echo "Construction de l'image Docker..."
                script {
                    bat "docker build -t ${DOCKER_IMAGE} ."
                    bat "docker tag ${DOCKER_IMAGE} ${APP_NAME}:latest"
                }
            }
        }
        
        stage('📤 Load to Minikube') {
            steps {
                echo "Chargement de l'image dans Minikube..."
                script {
                    bat "minikube image load ${DOCKER_IMAGE}"
                    bat "minikube image load ${APP_NAME}:latest"
                }
            }
        }
        
        stage('🚀 Deploy to Kubernetes') {
            steps {
                echo 'Déploiement sur Kubernetes...'
                script {
                    bat 'kubectl apply -f k8s/deployment.yaml'
                    bat 'kubectl apply -f k8s/service.yaml'
                    bat "kubectl set image deployment/tp-spring-boot tp-spring-boot=${DOCKER_IMAGE}"
                    bat 'kubectl rollout status deployment/tp-spring-boot'
                }
            }
        }
        
        stage('✅ Vérification') {
            steps {
                echo 'Vérification du déploiement...'
                bat 'kubectl get pods -l app=tp-spring-boot'
                bat 'kubectl get services tp-spring-boot-service'
            }
        }
    }
    
    post {
        success {
            echo '✅ Pipeline exécuté avec succès !'
            bat 'kubectl get all -l app=tp-spring-boot'
        }
        failure {
            echo '❌ Échec du pipeline !'
        }
        always {
            echo 'Nettoyage...'
            cleanWs()
        }
    }
}