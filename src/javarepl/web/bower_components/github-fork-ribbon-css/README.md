# "Fork me on GitHub" CSS ribbon

This is a recreation of the [Fork me on GitHub ribbon](https://github.com/blog/273-github-ribbons)
in CSS, hence resolution-independent.

## Using "Fork me on GitHub" CSS ribbon with a CDN

You can use github-fork-ribbon-css without installation via [cdnjs.com](https://cdnjs.com/libraries/github-fork-ribbon-css).

Copy the following code into the `<head>` of your page:


```html
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/github-fork-ribbon-css/0.2.0/gh-fork-ribbon.min.css" />
<!--[if lt IE 9]>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/github-fork-ribbon-css/0.2.0/gh-fork-ribbon.ie.min.css" />
<![endif]-->
```

And this into the `<body>` of your page:

```html
<a class="github-fork-ribbon" href="http://url.to-your.repo" title="Fork me on GitHub">Fork me on GitHub</a>
```

See 'em in action! <https://simonwhitaker.github.io/github-fork-ribbon-css/>

Feel free to fork, tweak and send me a pull request.

Note: this project is not sponsored or in any way endorsed by GitHub.
