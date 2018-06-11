/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launch;
import java.io.File;
import java.util.Properties;
import java.util.List;

import org.cdlib.mrt.utility.GetProp;
import org.cdlib.mrt.utility.LoggerInf;
import org.cdlib.mrt.utility.PropertiesUtil;
import org.cdlib.mrt.utility.StringUtil;
import org.cdlib.mrt.utility.TFileLogger;
import org.cdlib.mrt.utility.TFrame;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;

import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.StatusCommand;


 
public class Main {
    public static String branch = "mytest";
 
    public static void main(String[] args) throws Exception 
    {
        new Main().start();
    }
 
    public void start() 
            throws Exception 
    {
        TFrame tFrame = null;
        Git git = null;
        try {
            String propertyList[] = {
                "resources/GittestLogger.properties",
                "resources/Gittest.properties"};
            tFrame = new TFrame(propertyList, "InvLoad");
            Properties prop  = tFrame.getProperties();
            LoggerInf logger = new TFileLogger("testFormatter", 10, 10);
            System.out.println(PropertiesUtil.dumpProperties("myprop", prop));
            String repo = prop.getProperty("repo");
            if (repo != null) {
                File repoF = new File(repo);
                git =  Git.open(repoF);
                /*
                ListBranchCommand lbc = git.branchList();
                List<String> statusList = cmd.getPaths();
                for (String status : statusList) {
                    System.out.println("Status:" + status);
                }
*/
            }
            System.out.println("Start(" + branch +  "):" + prop.getProperty("form"));

        } catch(Exception e) {
                e.printStackTrace();
                System.out.println(
                    "Main: Encountered exception:" + e);
                System.out.println(
                        StringUtil.stackTrace(e));
        }
    }
    
    public static class Test { }
 
}