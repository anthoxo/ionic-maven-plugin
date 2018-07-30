package com.maven.ionic.project;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "build")
public class BuildMojo extends AbstractIonicMojo {

    @Parameter(property = "platform")
    String platform;

    public void execute() throws MojoExecutionException, MojoFailureException {
        runCommand = true;
        errorLog = "ionic cordova build doesn't work...";
        if (platform == null) {
            getLog().error("ionic cordova build need a platform...");
            getLog().error("Please use this command instead : mvn ionic:build -Dplatform=yourPlatform");
            System.exit(1);
        } else {
            String[] commands = {"ionic", "cordova", "build"};
            if (arguments == null) {
                command = MojoUtils.formattingCommand(commands, platform);
            } else {
                command = MojoUtils.formattingCommand(commands, platform + " " + arguments);
            }
            super.execute();
        }
    }
}
