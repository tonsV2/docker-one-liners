// https://jenkins.io/doc/book/pipeline/jenkinsfile/

node {
/*
    stage('Checkout') {
        git url: 'http://gitlab/project/docker-one-liners.git', branch: 'master'
    }

    stage('Run tests') {
        sh './mvnw clean test -Dspring.profiles.active=test'
    }

    stage('Package') {
        sh './mvnw package -DskipTests'

        def pom = readMavenPom file:'pom.xml'
        jarFilename = pom.name + "-" + pom.version + ".jar"
    }

    stage('Deploy jar') {
        sh "./mvnw deploy -DskipTests"
    }

    stage('Push docker image') {
        def app = docker.build "oneliner"
        docker.withRegistry('https://192.168.0.3:5000') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }
*/

    stage('Run tests') {
//        sh './mvnw clean test -Dspring.profiles.active=test'
    }

    stage('Deploy jar') {
          sh 'export IMAGE_NAME=tons/docker-oneliner:$BUILD_NUMBER'
          sh 'docker build -t $IMAGE_NAME .'

          sh 'export HEROKU_IMAGE_NAME=registry.heroku.com/glacial-spire-56714/web'
          sh 'docker login --username=_ --password=$HEROKU_API_KEY registry.heroku.com'
          sh 'docker tag $IMAGE_NAME $HEROKU_IMAGE_NAME'
          sh 'docker push $HEROKU_IMAGE_NAME'
    }

}
