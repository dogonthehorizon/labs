node-test-coverage-example
==========================

This is a simple example of setting up some typical test/code coverage tools.

In particular, this example uses the following libraries:

  - [mocha](https://npmjs.org/package/mocha)
  - [chai](https://npmjs.org/package/chai)
  - [blanket](https://npmjs.org/package/blanket)

### Running the example
  1. Make sure you have mocha installed globally.

  ```
  npm install -g mocha
  ```

  2. Install the project dependencies

  ```
  npm install
  ```

  3. Generate an HTML code coverage report, in addition to running tests.

  ```
  mocha -R html-cov ./test/arithmetic.js > coverage.html
  ```
  
  4. Open the results in your browser of choice. If you're on OSX, simply run:

  ```
  open coverage.html
  ```