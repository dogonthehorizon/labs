var gulp = require('gulp'),
    gutil = require('gulp-util'),
    sass = require('gulp-sass'),
    bourbon = require('node-bourbon').includePaths,
    watch = require('gulp-watch'),
    plumber = require('gulp-plumber'),
    nodemon = require('gulp-nodemon');

// SASS/Bourbon options
var sass_source = './resources/sass';
var sass_dist = './resources/public/css';
var sass_opts = {
    outputStyle: gutil.env.production ? 'compressed' : 'expanded',
    includePaths: [sass_source, sass_source + '/partials'].concat(bourbon),
    errLogToConsole: true,
    onSuccess: function(css) {
        console.log(css);
    },
    onError: function(css) {
        console.log('Something went wrong!');
    }
};

// Print the current environment to the console.
gutil.log('Environment: ', gutil.colors.blue(gutil.env.production ? 'Production' : 'Development'));

/**
 * The default task, currently just compiles SASS resources.
 *
 * @method default
 */
gulp.task('default', ['styles']);

/**
 * Responsible for compiling SASS resources using Bourbon into the appropriate CSS folders.
 *
 * @method styles
 */
gulp.task('styles', function() {
    return gulp.src(sass_source + '/main.scss')
               .pipe(sass(sass_opts))
               .pipe(gulp.dest(sass_dist));
});

/**
 * Responsible for watching SASS resources and updating them in their proper CSS destination.
 *
 * @method watch-sass
 */
gulp.task('watch-sass', function() {
    return gulp.src([sass_source, sass_source + '/partials/*.scss'])
               .pipe(watch())
               .pipe(plumber())
               .pipe(sass(sass_opts))
               .pipe(gulp.dest(sass_dist));
});

/**
 * Starts an instance of nodemon to reload the server on changes. Options contained in nodemon.json.
 *
 * @method server
 */
gulp.task('server', function() {
    nodemon({
        options: '-V',
        script: require('./package.json').main
    });
});
