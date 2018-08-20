package com.maven.ionic.project;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "serve")
public class ServeMojo extends AbstractIonicMojo {

    public void execute()
            throws MojoExecutionException, MojoFailureException {
        runCommand = true;
        errorLog = "ionic serve doesn't work";

        String[] ionicCommand = {"ionic", "serve"};
        command = MojoUtils.formattingCommand(ionicCommand, arguments);
        super.execute();
    }
}
