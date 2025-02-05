define(["require", "exports", "log", "util.roberta", "message", "program.model", "jquery", "bootstrap-table"], function (require, exports, LOG, UTIL, MSG, PROGRAM, $) {
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.init = void 0;
    function init() {
        //        initView();
        initEvents();
    }
    exports.init = init;
    function initView() { }
    function initEvents() {
        /**
         * Delete the programs that were selected in program list
         */
        $('#doDeleteProgram').onWrap('click', function () {
            var programs = $('#confirmDeleteProgram').data('programs');
            for (var i = 0; i < programs.length; i++) {
                var prog = programs[i];
                var progName = prog[0];
                var progOwner = prog[1];
                var progRight = prog[2];
                var author = prog[3];
                if (progRight.sharedFrom) {
                    PROGRAM.deleteShare(progName, progOwner, author, function (result, progName) {
                        UTIL.response(result);
                        if (result.rc === 'ok') {
                            MSG.displayInformation(result, 'MESSAGE_PROGRAM_DELETED', result.message, progName);
                            $('#progList').find('button[name="refresh"]').clickWrap();
                            LOG.info('remove shared program "' + progName + '"form List');
                        }
                    });
                }
                else {
                    PROGRAM.deleteProgramFromListing(progName, author, function (result, progName) {
                        UTIL.response(result);
                        if (result.rc === 'ok') {
                            MSG.displayInformation(result, 'MESSAGE_PROGRAM_DELETED', result.message, progName);
                            $('#progList').find('button[name="refresh"]').clickWrap();
                            LOG.info('delete program "' + progName);
                        }
                    });
                }
            }
            $('.modal').modal('hide');
        }),
            'delete programs clicked';
    }
});
