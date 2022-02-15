# Gatling Practice #1

To run the script from command line:

cd <your_local_path>/demoAdvancedIk

mvn gatling:test -Dgatling.simulationClass=demoAdvancedIk.DemoAdvanced -Dusers=1 -Dduration=60

To run the script from the Jenkins pipeline:

pipeline {
    agent any
    tools {
      maven 'Maven3'
    }
    stages {
        stage("Gatling run") {
            steps {
                sh "cd <your_local_path>/demoAdvancedIk; mvn gatling:test -Dgatling.simulationClass=demoAdvancedIk.DemoAdvanced -Dusers=$users -Dduration=$duration"
            }
        }
    }
}
