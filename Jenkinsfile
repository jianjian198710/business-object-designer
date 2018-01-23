#!/usr/bin/env groovy
@Library('piper-lib') _

try {
    // pull request voting
    if (env.BRANCH_NAME.startsWith('PR')) {
        stage('Pull-request voting') {
            node {
                deleteDir()
                checkout scm

                setupPipelineEnvironment script: this, storeGithubStatistics: false

                measureDuration(script: this, measurementName: 'voter_duration') {
                    executeDocker(dockerImage: 'docker.wdf.sap.corp:50000/piper/maven', dockerWorkspace: '/home/piper') {
                        sh 'mvn --batch-mode clean install'
                    }

                    publishCheckResults archive: true, tasks: true, pmd: true, cpd: true, findbugs: true, checkstyle: true, aggregation: [thresholds: [fail: [high: 0]]]
                    publishTestResults junit: [updateResults: true, archive: true], jacoco: [archive: true, pattern: '**/target/coverage-reports/aggregate.exec']
                }
            }
        }
    }else if (env.BRANCH_NAME == 'dev') {
        stage('Central Build') {
            lock(resource: "${env.JOB_NAME}/10", inversePrecedence: true) {
                milestone 10
                node {
                    deleteDir()
                    checkout scm

                    setupPipelineEnvironment script: this, storeGithubStatistics: true

                    measureDuration(script: this, measurementName: 'build_duration') {
                        setVersion script: this, buildTool: 'maven', artifactType: 'mta', gitExecute:true
                        stashFiles(script: this) {
                            executeBuild script: this, buildType: 'xMakeStage'
                        }
                    }
                }
            }
        }
    }
} catch (Throwable err) { // catch all exceptions
    globalPipelineEnvironment.addError(this, err)
    throw err
} finally {
    node{
        writeInflux script: this
        sendNotificationMail script: this
    }
}
