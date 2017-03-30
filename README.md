# coala for Eclipse

[![Build Status](https://travis-ci.org/coala/coala-eclipse.svg?branch=master)](https://travis-ci.org/coala/coala-eclipse)

This package offers a plugin for the Eclipse to use the code analysis software [coala](https://github.com/coala/coala) inside the IDE.

## Installation

<a href="http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=3100683" class="drag" title="Drag to your running Eclipse workspace."><img class="img-responsive" src="https://marketplace.eclipse.org/sites/all/themes/solstice/public/images/marketplace/btn-install.png" alt="Drag to your running Eclipse workspace." /></a>

Drag the above button into your running Eclipse workspace and accept the terms to install.

*If you're unable to view the button install the plug-in from the [Eclipse Marketplace](https://marketplace.eclipse.org/content/coala-eclipse-plug).*

### Build developmental version
```text
mvn clean verify
```

## Unistall
1. In Eclipse go to Help-> Installation Details -> Installed Software.
2. Select coala.
3. Click uninstall.

## Using the plug-in

*The plug-in requires the coala binaries to be present on your system. If you haven't already installed coala you can read the installation instructions [here](https://github.com/coala/coala#installation).*

There are two ways in which you can run coala from the Eclipse plug-in:
* Using a `coafile`.
* Manually selecting a bear to run the analysis with.

### Running coala using a [`coafile`](http://coala.readthedocs.io/en/latest/Users/coafile.html)

1. Fire up Eclipse and open a file that you want to run the analysis on.

2. Make sure you have configured the `coafile`. Learn more about that [here](http://coala.readthedocs.io/en/latest/Users/coafile.html).

3. Click the `coala` button on the tool-bar.

4. The plug-in will then run the analysis and display the results in the `Problems` pane.

### Running coala by manually selecting the bear of your choice

1. Fire up Eclipse and open a file that you want to run the analysis on.

2. Right click anywhere inside the editor window to open the context menu.

3. In the `Run coala with` push menu, select the the bear you wish to run.

4. The plug-in will then run the analysis and display the results in the `Problems` pane.

### Fixing issues returned after analysis

1. After the analysis is complete and the issues are visible in the `Problems` pane, right-click an issue and select the `Quick Fix` option (*Ctrl +1* keyboard shortcut).

2. In the `Quick Fix` dialog, select the fix that you want to apply and click `Finish`.

3. The issue will be fixed and no longer visible in the `Problems` pane.

## License

![AGPL](https://img.shields.io/github/license/coala/coala.svg)
