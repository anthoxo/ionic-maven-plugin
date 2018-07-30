package com.maven.ionic.project;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Mojo(name = "install", defaultPhase = LifecyclePhase.PROCESS_RESOURCES)
public class InstallMojo extends AbstractIonicMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {
        String line;
        Process pr;
        BufferedReader reader;
        try {
            /**
             * check if node is installed
             */
            if (this.checkInstalled("node")) {
                getLog().info("node is installed !");
            } else {
                getLog().error("Please install node before continuing");
                System.exit(1);
            }

            /**
             * check if npm is installed
             */
            if (this.checkInstalled("npm")) {
                getLog().info("npm is installed !");
            } else {
                getLog().error("Please install npm before continuing");
                System.exit(1);
            }

            /**
             * install ionic
             */
            getLog().info("Check a new version of Ionic");
            getLog().info("Running on " + OsUtils.getOsName());
            String ionicInstallCommand = null;
            if (OsUtils.isWindows() || OsUtils.isMac() || OsUtils.isUnix()) {
                ionicInstallCommand = "npm install -g ionic";
            } else {
                getLog().error("Your OS is not supported by this plugin...");
                System.exit(1);
            }
            pr = Runtime.getRuntime().exec(ionicInstallCommand);
            reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            while ((line = reader.readLine()) != null) {
                getLog().info(line);
            }

            getLog().info("Installation will be on " + workingDirectory);

            pr = Runtime.getRuntime().exec("npm install", null, new File(workingDirectory));
            reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            while ((line = reader.readLine()) != null) {
                getLog().info(line);
            }

            pr = Runtime.getRuntime().exec("ionic cordova prepare --no-build", null, new File(workingDirectory));
            reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            while ((line = reader.readLine()) != null) {
                getLog().info(line);
            }
            getLog().info("Preparation : done !");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkInstalled(String command) throws IOException {
        Process pr = Runtime.getRuntime().exec("which " + command);
        BufferedReader readerInfo = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        if (readerInfo.readLine() == null) {
            return false;
        } else {
            return true;
        }
    }

}
