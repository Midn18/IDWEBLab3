pipeline{
    agent any

    environment{
        ON_SUCCESS_SEND_EMAIL = true
        ON_FAILURE_SEND_EMAIL = true
    }

    stages{
        stage('Build'){
            steps{
                echo 'Building'
                bat 'mvn install'
                bat 'mvn spring-boot:run'
                bat 'npm install'
                bat 'ng serve'
            }
        }
        stage('Test'){
            steps{
                echo 'Testing'
            }
        }
    }
}