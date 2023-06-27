node {
    def dockerImageTag = "springboot-deploy${env.BUILD_NUMBER}"
    try{
    //notifyBuild('STARTED')
        stage('Clone Repo') {
            checkout scm
            echo "Cloning branch: ${env.BRANCH_NAME}"
        }
        stage('Build docker') {
            echo "Docker Image Tag Name: ${dockerImageTag}"
            sh "mvn clean package -DskipTests"
            sh "ls"
            dockerImage = docker.build("springboot-deploy:${env.BUILD_NUMBER}")
        }
        stage('Deploy docker'){
            echo "Docker Image Tag Name: ${dockerImageTag}"
            sh "docker stop springboot-deploy || true && docker rm springboot-deploy || true"
            sh "docker run --name springboot-deploy -d -p8083:8083 springboot-deploy:${env.BUILD_NUMBER}"
        }
        stage('Integration test'){
            sh "mvn clean test"
        }
        stage('Report'){
            allure includeProperties: false, jdk: '', reportBuildPolicy: 'ALWAYS', results: [[path: 'target/allure-results']]
        }
    }catch(e){
//      currentBuild.result = "FAILED"
        throw e
    }finally{
//      notifyBuild(currentBuild.result)
    }
}