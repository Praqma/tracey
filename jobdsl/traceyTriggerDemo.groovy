import jenkins.*
import jenkins.model.*

def addDefaultParameters(def context, buildsToKeep=50, artifactsToKeep=10, timeoutVal=40) {
    // Add timestamps and timeouts
    context.wrappers {
        timestamps()
        timeout {
            absolute(timeoutVal)
        }
    }
    // Set log rotator
    context.logRotator {
        numToKeep(buildsToKeep)
        artifactNumToKeep(artifactsToKeep)
    }
}

def addGitSCM(def context, repoURL, branchName='master', credentialsId='') {
    context.scm {
        git{
            remote {
                name('origin')
                url(repoURL)
                credentials(credentialsId)
            }
            branch(branchName)
            extensions {
                cleanBeforeCheckout()
            }
        }
    }
}

filterRegEx = '\"type\":\\s*\"EiffelSourceChangeCreatedEvent\"'
envInjRegEx = '''BRANCH_FROM_MESSAGE \"branch":\\s+\"([^"']+)\"
REPO_FROM_MESSAGE \"repoUri\":\\s+\"([^"']+)\"
'''

job("tracey-demo-scm-trigger") {
    addDefaultParameters(delegate)
    addGitSCM(delegate, "\$REPO_FROM_MESSAGE_1", "\$BRANCH_FROM_MESSAGE_1")
        triggers {
            tracey('tracey', 'direct', 'rabbit-host-id') {
            injectEnvironment {
                payloadKey 'MESSAGE'
                payloadInjection envInjRegEx
            }
            filters {
                payloadRegex filterRegEx
            }
        }
    }
    steps {
        shell('env')
    }
}
