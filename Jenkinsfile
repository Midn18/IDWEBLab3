pipeline{
    agent any
    environment{
        ON_SUCCESS_SEND_EMAIL = "true"
        ON_FAILURE_SEND_EMAIL = "true"
    }

    stages{
        stage('Build'){
            steps{
                echo 'Building'
                bat 'mvn install -DskipTests --file ./backend'
                bat 'npm install %WORKSPACE%\\frontend'
            }
        }
        stage('Testing backend'){
            steps{
                echo 'Running backend tests'
                bat 'mvn test --file ./backend'
                junit allowEmptyResults: true, testResults: '**\\surefire-reports\\**.xml'
                echo 'Backend tests finished execution'
            }
        }
        stage('Testing frontend'){
            when{
                expression { TESTING_FRONTEND == "true" }
            }
            steps{
                echo "Testing frontend ${TESTING_FRONTEND}"
            }
        }
        stage('Delete workspace'){
            when{
                expression { CLEAN_WORKSPACE == "true" }
            }
            steps{
                echo "Deleting workspace ${CLEAN_WORKSPACE}"
                deleteDir()
            }
        }
    }
    post{
        success{
            emailext body: 'Build finished successfully',
            subject: "Jenkins Build ${currentBuild.currentResult} : Job ${env.JOB_NAME}",
            to: 'mihail.danilenco18@gmail.com'
        }
        failure{
            emailext body: 'Build finished with failure',
            subject: "Jenkins Build ${currentBuild.currentResult} : Job ${env.JOB_NAME}",
            to: 'mihail.danilenco18@gmail.com'
        }
    }
}