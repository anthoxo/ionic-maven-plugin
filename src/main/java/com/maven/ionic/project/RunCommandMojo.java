package com.maven.ionic.project;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "runCommand")
public class RunCommandMojo extends AbstractIonicMojo {
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info(workingDirectory);
    }
}