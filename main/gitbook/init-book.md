## //www.chengweiyang.cn/gitbook/customize/book.json.html 
## //caibaojian.com/gitbook/format/plugins.html
## Setup

1. Install node.js and npm package.

        # Mac OSX.
        $ brew install node npm

        # Ubuntu.
        $ sudo apt-get install nodejs npm

1. Install [n](https://github.com/tj/n) with npm.

        $ sudo cnpm install n -g
        
1. Downgrade node.js 

        $ n 5.12.0        

1. Install gitbook-cli package.

        $ sudo cnpm install -g gitbook-cli

1. Install necessary package with npm.

        $ gitbook install

1. Check version.

        $ gitbook -V
        CLI version: 2.3.0
        GitBook version: 3.1.1
 
## Build

1. Go to `MyBook` project.

        $ cd ${MyBook}

1. Build static HTML.

        $ make

1. Build PDF.

        $ make pdf

1. Build epub for iDevice.

        $ make epub

1. Build mobi for Kindle.

        $ make mobi

1. Review result at local.

        $ make review

1. Clean.

        $ make clean
