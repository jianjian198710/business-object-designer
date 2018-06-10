pipeline {
	agent { 
		docker { 
			image 'maven:3.3.3' 
		} 
	}
	stages {
		stage('check'){
			steps {
                       		step([$class: 'hudson.plugins.checkstyle.CheckStylePublisher', pattern: '**/target/checkstyle-result.xml', unstableTotalAll:'0',unhealthy:'100', healthy:'100'])
                        	step([$class: 'PmdPublisher', pattern: '**/target/pmd.xml'])
                        	step([$class: 'FindBugsPublisher', pattern: '**/findbugsXml.xml'])

			}
		}
		stage('build') {
			steps {
				sh 'mvn -B -DskipTests clean package'
			}
		}
		
		stage('test') {
			steps {
				sh 'mvn clean verify'
			}
		}
	}
}
