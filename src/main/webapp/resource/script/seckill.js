//瀛樻斁涓昏浜や簰閫昏緫鐨刯s浠ｇ爜
// javascript 妯″潡鍖�(package.绫�.鏂规硶)

var seckill = {

    //灏佽绉掓潃鐩稿叧ajax鐨剈rl
    URL: {
        now: function () {
            return '/myseckill/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/myseckill/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return '/myseckill/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },

    //楠岃瘉鎵嬫満鍙�
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;//鐩存帴鍒ゆ柇瀵硅薄浼氱湅瀵硅薄鏄惁涓虹┖,绌哄氨鏄痷ndefine灏辨槸false; isNaN 闈炴暟瀛楄繑鍥瀟rue
        } else {
            return false;
        }
    },

    //璇︽儏椤电鏉�閫昏緫
    detail: {
        //璇︽儏椤靛垵濮嬪寲
        init: function (params) {
            //鎵嬫満楠岃瘉鍜岀櫥褰�,璁℃椂浜や簰
            //瑙勫垝鎴戜滑鐨勪氦浜掓祦绋�
            //鍦╟ookie涓煡鎵炬墜鏈哄彿
            var userPhone = $.cookie('userPhone');
            //楠岃瘉鎵嬫満鍙�
            if (!seckill.validatePhone(userPhone)) {
                //缁戝畾鎵嬫満 鎺у埗杈撳嚭
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true,//鏄剧ず寮瑰嚭灞�
                    backdrop: 'static',//绂佹浣嶇疆鍏抽棴
                    keyboard: false//鍏抽棴閿洏浜嬩欢
                });

                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    console.log("inputPhone: " + inputPhone);
                    if (seckill.validatePhone(inputPhone)) {
                        //鐢佃瘽鍐欏叆cookie(7澶╄繃鏈�)
                        $.cookie('userPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //楠岃瘉閫氳繃銆�銆�鍒锋柊椤甸潰
                        window.location.reload();
                    } else {
                        //todo 閿欒鏂囨淇℃伅鎶藉彇鍒板墠绔瓧鍏搁噷
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">鎵嬫満鍙烽敊璇�!</label>').show(300);
                    }
                });
            }

            //宸茬粡鐧诲綍
            //璁℃椂浜や簰
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    //鏃堕棿鍒ゆ柇 璁℃椂浜や簰
                    seckill.countDown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result: ' + result);
                    alert('result: ' + result);
                }
            });
        }
    },

    handlerSeckill: function (seckillId, node) {
        //鑾峰彇绉掓潃鍦板潃,鎺у埗鏄剧ず鍣�,鎵ц绉掓潃
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">寮�濮嬬鏉�</button>');

        $.get(seckill.URL.exposer(seckillId), {}, function (result) {
            //鍦ㄥ洖璋冨嚱鏁扮鎵ц浜や簰娴佺▼
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //寮�鍚鏉�
                    //鑾峰彇绉掓潃鍦板潃
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    console.log("killUrl: " + killUrl);
                    //缁戝畾涓�娆＄偣鍑讳簨浠�
                    $('#killBtn').one('click', function () {
                        //鎵ц绉掓潃璇锋眰
                        //1.鍏堢鐢ㄦ寜閽�
                        $(this).addClass('disabled');//,<-$(this)===('#killBtn')->
                        //2.鍙戦�佺鏉�璇锋眰鎵ц绉掓潃
                        $.post(killUrl, {}, function (result) {
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                //鏄剧ず绉掓潃缁撴灉
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        });
                    });
                    node.show();
                } else {
                    //鏈紑鍚鏉�(娴忚鍣ㄨ鏃跺亸宸�)
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.countDown(seckillId, now, start, end);
                }
            } else {
                console.log('result: ' + result);
            }
        });

    },

    countDown: function (seckillId, nowTime, startTime, endTime) {
        console.log(seckillId + '_' + nowTime + '_' + startTime + '_' + endTime);
        var seckillBox = $('#seckill-box');
        if (nowTime > endTime) {
            //绉掓潃缁撴潫
            seckillBox.html('绉掓潃缁撴潫!');
        } else if (nowTime < startTime) {
            //绉掓潃鏈紑濮�,璁℃椂浜嬩欢缁戝畾
            var killTime = new Date(startTime + 1000);//todo 闃叉鏃堕棿鍋忕Щ
            seckillBox.countdown(killTime, function (event) {
                //鏃堕棿鏍煎紡
                var format = event.strftime('绉掓潃鍊掕鏃�: %D澶� %H鏃� %M鍒� %S绉� ');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                //鏃堕棿瀹屾垚鍚庡洖璋冧簨浠�
                //鑾峰彇绉掓潃鍦板潃,鎺у埗鐜板疄閫昏緫,鎵ц绉掓潃
                console.log('______fininsh.countdown');
                seckill.handlerSeckill(seckillId, seckillBox);
            });
        } else {
            //绉掓潃寮�濮�
            seckill.handlerSeckill(seckillId, seckillBox);
        }
    }

}