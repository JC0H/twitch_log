$(function () {

    var getInfo = function (callback) {
        Twitch.getStatus(function(err, status) {
            callback(channel);
        });
    }

    Twitch.init({clientId: '1vurum8ri4igxgk7oty6d3l047mtad'}, function(error, status) {

        console.log(status);
        if (status.authenticated){
            $('.twitch-disconnect').hide();
            getInfo(function (data) {
                $('strong').text(data.display_name);
                $('#visit').text('Visite my channel').attr('href','https://www.twitch.tv/gogiandrosian');
            })
        } else {
            $('#login-info').hide();
        }
    });

    var chekStatus = function () {
        Twitch.getStatus(function(err, status) {
            console.log(status);
        });
    }

    chekStatus();

    var login = function () {
        Twitch.login({
            scope: ['user_read', 'channel_read']
        });
    }

    var logout = function () {

    }

    $('.twitch-connect').click(function (e) {
        e.preventDefault();

        login();
    })

    $('.twitch-disconnect').click(function (e) {
        e.preventDefault();

        logout();
    })
})