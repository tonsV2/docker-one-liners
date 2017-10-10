node {
    stage('Checkout') {
        git url: 'http://gitlab/project/docker-one-liners.git', branch: 'master'
    }
/*
    stage('Run tests') {
        sh './mvnw clean test -Dspring.profiles.active=test'
    }
*/
    stage('Package') {
        sh './mvnw package -DskipTests'

        def pom = readMavenPom file:'pom.xml'
        jarFilename = pom.name + "-" + pom.version + ".jar"
    }

    stage('Publish jar') {
        sh "./mvnw deploy -DskipTests"
    }

    stage('Push docker image') {
        def app = docker.build "oneliner"
        docker.withRegistry('https://192.168.0.3:5000') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }
}

