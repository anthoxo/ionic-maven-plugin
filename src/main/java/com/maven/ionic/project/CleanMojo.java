package com.maven.ionic.project;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;

@Mojo(name = "clean", defaultPhase = LifecyclePhase.CLEAN)
public class CleanMojo extends AbstractIonicMojo {

    @Parameter(property = "force", defaultValue = "false")
    boolean force;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Deleting useless files and folders...");
        try {
            Runtime.getRuntime().exec("rm -rf platforms");
            Runtime.getRuntime().exec("rm -rf plugins");
            Runtime.getRuntime().exec("rm -rf www");
            if (force) {
                getLog().info("Deleting target folder");
                Runtime.getRuntime().exec("rm -rf target");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
