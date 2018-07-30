package com.maven.ionic.project;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "run")
public class RunMojo extends AbstractIonicMojo {

    @Parameter(property = "platform")
    String platform;

    public void execute() throws MojoExecutionException, MojoFailureException {
        runCommand = true;
        errorLog = "ionic cordova run doesn't work";
        if (platform == null) {
            getLog().error("ionic cordova run need a platform...");
            getLog().error("Please use this command instead : mvn ionic:run -Dplatform=yourPlatform");
            System.exit(1);
        } else {
            String[] commands = {"ionic", "cordova", "run"};
            if (arguments == null) {
                command = MojoUtils.formattingCommand(commands, platform);
            } else {
                command = MojoUtils.formattingCommand(commands, platform + " " + arguments);
            }
            super.execute();
        }
    }
}
