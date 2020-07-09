// JavaScript Document


    // -------------------------------------------------------------------
    // Demo page code START, you can ignore this in your implementation
    $(function () {
        var noDisplay = {
            listview: true,
            forms: true,
            menustrip: 'partial'
        };

        function init() {
            var demo = $('#demo').val(),
                component = demo.split(/(?=[A-Z])/)[0],
                demoContainer = $('.demo-wrapper-' + demo);

            // reset options
            resetOptions(component);

            // hide all demos
            $('.demo-wrapper').hide();

            // show current demo
            demoContainer.show();

            // reinitialize demo
            window[demo + 'Init']();

            demoContainer.trigger('mbsc-enhance');
        }

        function resetOptions(component) {
            var displayValue = $('#display').val();

            // reset display
            $('#display').parent().show();
            $('#display option').show();

            if (noDisplay[component] === 'partial') {
                if (displayValue == 'modal' || displayValue == 'bubble') {
                    $('#display').val('inline');
                }
                $('#display option[value="modal"]').hide();
                $('#display option[value="bubble"]').hide();
            } else if (noDisplay[component] === true) {
                $('#display').parent().hide();
            }
        }

        $('#main').find('.settings').change(init);

        init();
    });
    // Demo page code END
    // -------------------------------------------------------------------

        /* Create, remove, update demo script */
        function listviewUpdateInit() {
            $(function () {
                
                var ids = 6;

                // Mobiscroll Listview initialization
                $('#listviewUpdate-demo').mobiscroll().listview({
                    theme: 'ios',      // Specify theme like: theme: 'ios' or omit setting to use default
                    lang: 'zh',    // Specify language like: lang: 'pl' or omit setting to use default
                    mode: 'listviewUpdate',
                    sortable: false,                // More info about sortable: https://docs.mobiscroll.com/3-0-0_beta2/listview#!opt-sortable
                    iconSlide: true,               // More info about iconSlide: https://docs.mobiscroll.com/3-0-0_beta2/listview#!opt-iconSlide
                    striped: true,                 // More info about striped: https://docs.mobiscroll.com/3-0-0_beta2/listview#!opt-striped
                    stages: [ {
                        percent: -10,
                        color: 'red',
                        text: '删除',
                        confirm: true,
                        action: function (event, inst) {
                        	var rowId = event.target.id;
                        	$("#"+rowId).attr("onclick","");
                        	$.ajax({
                        		url:getRequestUrl("/RepairOrderInfoController/deleteRepairCostDetailTempForRepairMob.json"),
                        		dataType:"json",
                        		data:{"repairCostDetailTemp.repair_cost_id":rowId},
                        		success:function(result){
                        			if(result.opflag){
                        				inst.remove(event.target);
                                        return false;
                        			}
                        		},
                        		error:function(error){
                        			layer.closeAll('loading');
                        			lalert('网络原因操作失败！','error');
                        		}
                        	});
                        }
                    }],
                    onItemAdd: function () {       // More info about onItemAdd: https://docs.mobiscroll.com/3-0-0_beta2/listview#!event-onItemAdd
                        $('#listviewUpdate-demo_note').hide();
                    },
                    onItemRemove: function () {    // More info about onItemRemove: https://docs.mobiscroll.com/3-0-0_beta2/listview#!event-onItemRemove
                        if ($('li', this).length < 2) {
                            $('#listviewUpdate-demo_note').show();
                        }
                    }
                });
            
                $('#listviewUpdate-demo_note').click(function () {
                    window.location.reload();
                });
            
            });
        }

       

