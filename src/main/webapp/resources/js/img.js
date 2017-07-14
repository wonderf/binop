/**
 * Created by Stas on 08.12.2016.
 */
function screen() {
    var img = [];
    img[0] = 'https://media.giphy.com/media/Z5WLaZ8cs3Jf2/giphy.gif';
    img[1] = 'https://media.giphy.com/media/4q11PwNfzT2BG/giphy.gif';
    img[2]='https://media.giphy.com/media/H9nnXKNfa5wf6/giphy.gif';
    img[3]='https://media.giphy.com/media/l0HlweKafsHmQihJC/giphy.gif';
    img[4]='https://media.giphy.com/media/jou4Cd2mx1lGU/giphy.gif';
    img[5]='https://media.giphy.com/media/BEob5qwFkSJ7G/giphy.gif';
    img[6]='https://media.giphy.com/media/2rtQMJvhzOnRe/giphy.gif';
    img[7]='https://media.giphy.com/media/104nHkfZphvUL6/giphy.gif';
var rand = Math.floor(Math.random() * img.length);
document.getElementById("screen").src = img[rand];
}