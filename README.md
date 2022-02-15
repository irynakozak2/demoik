# Gatling Practice #1

To run the script from command line:

cd <your_local_path>/demoAdvancedIk

<pre><code>mvn gatling:test -Dgatling.simulationClass=demoAdvancedIk.DemoAdvanced -Dusers=1 -Dduration=60
</code></pre>


To run the script from the Jenkins pipeline:

<pre><code>
pipeline {
    agent any
    tools {
      maven 'Maven3'
    }
    stages {
        stage("Gatling run") {
            steps {
                sh "cd your_local_path/demoAdvancedIk; mvn gatling:test -Dgatling.simulationClass=demoAdvancedIk.DemoAdvanced -Dusers=$users -Dduration=$duration"
            }
        }
    }
}
</code></pre>

Generate Maven Archetype:

<pre><code>
mvn archetype:generate -DarchetypeGroupId=io.gatling.highcharts -DarchetypeArtifactId=gatling-highcharts-maven-archetype
</code></pre>
