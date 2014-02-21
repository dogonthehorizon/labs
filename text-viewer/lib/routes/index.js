exports.index = function(req, res) {
    return res.render('index',
        {
            title: 'Pride & Prejudice',
            bookTitle: 'Pride & Prejudice',
            currentChapter: 'Chapter 1'

        }
    );
};