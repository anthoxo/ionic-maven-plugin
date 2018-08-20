package com.maven.ionic.project;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "generate")
public class GenerateMojo extends AbstractIonicMojo {

    @Parameter(property = "component")
    String component;

    @Parameter(property = "name")
    String name;

    public void execute() throws MojoExecutionException, MojoFailureException {
        runCommand = false;
        errorLog = "ionic generate doens't work...";
        if (component == null || name == null) {
            getLog().error("ionic generate need a component and a name...");
            getLog().error("Please use this command instead : mvn ionic:generate -component=aComponent -Dcomponent.name=yourName");
            System.exit(1);
        } else {
            String[] commands = {"ionic", "generate"};
            if (arguments == null) {
                command = MojoUtils.formattingCommand(commands, component + " " + name);
            } else {
                command = MojoUtils.formattingCommand(commands, component + " " + name + " " + arguments);
            }
            super.execute();
        }
    }

}
