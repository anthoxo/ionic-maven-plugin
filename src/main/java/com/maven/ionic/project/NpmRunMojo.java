package com.maven.ionic.project;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "npmRun")
public class NpmRunMojo extends AbstractIonicMojo {
    @Parameter(property = "npmGoal")
    String npmGoal;

    public void execute() throws MojoExecutionException, MojoFailureException {
        runCommand = true;
        errorLog = "npm run doesn't work";
        String[] commands = {"npm", "run"};
        if (npmGoal == null) {
            getLog().error("This command need a npm goal as the following : mvn ionic:npmRun -Dnpm.goal=myGoal");
            System.exit(1);
        } else {
            if (arguments == null) {
                command = MojoUtils.formattingCommand(commands, npmGoal);
            }
            else {
                command = MojoUtils.formattingCommand(commands, npmGoal + " " + arguments);
            }
            super.execute();
        }

    }

}
