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
        when{
             expression { ON_SUCCESS_SEND_EMAIL == "true" }
        }
        steps{
            success{
                mail body: "Build finished successfully, see ${BUILD_URL}",
                subject: "Jenkins Build ${currentBuild.currentResult} : Job ${env.JOB_NAME} Build Number ${env.BUILD_NUMBER}",
                to: 'mihail.danilenco18@gmail.com'
            }
        }
        when{
             expression { ON_FAILURE_SEND_EMAIL == "true" }
            }
        steps{
            failure{
                mail body: "Build finished successfully, see ${BUILD_URL}",
                subject: "Jenkins Build ${currentBuild.currentResult} : Job ${env.JOB_NAME} Build Number ${env.BUILD_NUMBER}",
                to: 'mihail.danilenco18@gmail.com'
            }
        }
    }
}