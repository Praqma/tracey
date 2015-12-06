public class JobHelpers{
   /*
   * Use this function to add log rotation to description section in job configuration
   *
   * @param context     Job instance
   */

    static void addDefaultSettings(context){
        context.logRotator {
            numToKeep(20)
        }
    }

    /*
   * Use this function to configure SCM block to job configuration
   *
   * @param context          Job instance
   * @param projecturl       URL to Gitlab project
   * @param credential       Credentials for access
   * @param branchnames      Specify branches
   * @param browsertype      Specify browser type
   * @param browserversion   and version
   * @param isRecursive      add recursive
   */
    // Branchnames array because of it can be many branches for job
    public static void addScmBlock(context, projecturl, credential, def branchnames=[] as String[], browsertype='', browserversion='', isRecursive=false){
        context.scm {
            git {
                remote {
                    url(projecturl)
                    credentials(credential)
                }
                for(branchname in branchnames) {
                    branch(branchname)
                }
                browser {
                   gitLab(browsertype, browserversion)
                }
                recursiveSubmodules isRecursive
            }
        }
    }


   /*
   * Use this function to configure pretested integration for the job
   *
   * @param context     Job instance
   */
   public static void addpretestedIntegration(context){
       context.configure { project ->
           project / publishers << 'org.jenkinsci.plugins.pretestedintegration.PretestedIntegrationPostCheckout' {}
           project / buildWrappers << 'org.jenkinsci.plugins.pretestedintegration.PretestedIntegrationBuildWrapper' {
              scmBridge('class': 'org.jenkinsci.plugins.pretestedintegration.scm.git.GitBridge'){
                branch 'master'
                integrationStrategy('class':'org.jenkinsci.plugins.pretestedintegration.scm.git.SquashCommitStrategy')
                repoName 'origin'  
              }
              rollbackEnabled false  
           }
        }
    }
}