package com.maven.ionic.project;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class AbstractIonicMojo extends AbstractMojo {

    @Parameter(property = "workingDirectory", defaultValue = "${project.build.directory}")
    public String workingDirectory;

    @Parameter(property = "arguments")
    public String arguments;

    public String[] command;
    boolean runCommand;
    String errorLog;

    public void execute() throws MojoExecutionException, MojoFailureException {
        String log;

        try {
            Process pr;
            if (runCommand) {
                pr = Runtime.getRuntime().exec(this.command, null, new File(workingDirectory));

            } else {
                pr = Runtime.getRuntime().exec(this.command);
            }
            BufferedReader readerInfo = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            BufferedReader readerDanger = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            while ((log = readerInfo.readLine()) != null) {
                getLog().info(log);
            }

            while ((log = readerDanger.readLine()) != null) {
                getLog().error(log);
            }
            pr.waitFor();

        } catch (IOException e) {
            getLog().error(errorLog);
        } catch (InterruptedException e) {
            getLog().error(errorLog);
        }
    }
}
