<template>
    <div class="tinymce-container editor-container" :class="{fullscreen:fullscreen}">
        <textarea class="tinymce-textarea" :id="tinymceId" placeholder="请输入内容"></textarea>
    </div>
</template>

<script>
    import plugins from './plugins'
    import toolbar from './toolbar'
    import './zh_CN'

    export default {
        name: 'tinymce',
        props: {
            id: {
                type: String
            },
            value: {
                type: String,
                default: ''
            },
            toolbar: {
                type: Array,
                required: false,
                default() {
                    return []
                }
            },
            menubar: {
                default: 'file edit insert view format table'
            },
            height: {
                type: Number,
                required: false,
                default: 360
            }
        },
        data() {
            return {
                hasChange: false,
                hasInit: false,
                tinymceId: this.id || 'vue-tinymce-' + +new Date(),
                fullscreen: false
            }
        },
        watch: {
            value(val) {
                    this.$nextTick(() =>
                        window.tinymce.get(this.tinymceId).setContent(val || ''))
            }
        },
        mounted() {
            this.initTinymce()
        },
        activated() {
            this.initTinymce()
        },
        deactivated() {
            this.destroyTinymce()
        },
        methods: {
            initTinymce() {
                const _this = this
                window.tinymce.init({
                    selector: `#${this.tinymceId}`,
                    height: this.height,
                    body_class: 'panel-body ',
                    object_resizing: false,
                    toolbar:  toolbar,
                    remove_script_host:false,
                    relative_urls:false,
                    menubar: this.menubar,
                    plugins: plugins,
                    end_container_on_empty_block: true,
                    powerpaste_word_import: 'clean',
                    // code_dialog_height: '500',
                    // code_dialog_width: '820',
                    advlist_bullet_styles: 'square',
                    advlist_number_styles: 'default',
                    imagetools_cors_hosts: ['www.tinymce.com', 'codepen.io'],
                    default_link_target: '_blank',
                    link_title: false,
                    fontsize_formats: "8pt 10pt 12pt 14pt 18pt 24pt 36pt",
                    font_formats : "宋体=宋体;新宋体=新宋体;仿宋_GB2312=仿宋_GB2312;微软雅黑=microsoft yahei;黑体=黑体;楷体_GB2312=楷体_GB2312;隶书=隶书;幼圆=幼圆;Andale Mono=andale mono,monospace;Arial=arial,helvetica,sans-serif;Arial Black=arial black,sans-serif;Book Antiqua=book antiqua,palatino,serif;Comic Sans MS=comic sans ms,sans-serif;Courier New=courier new,courier,monospace;Georgia=georgia,palatino,serif;Helvetica=helvetica,arial,sans-serif;Impact=impact,sans-serif;Symbol=symbol;Tahoma=tahoma,arial,helvetica,sans-serif;Terminal=terminal,monaco,monospace;Times New Roman=times new roman,times,serif;Trebuchet MS=trebuchet ms,geneva,sans-serif;Verdana=verdana,geneva,sans-serif;Webdings=webdings;Wingdings=wingdings,zapf dingbats;",
                    nonbreaking_force_tab: true, // inserting nonbreaking space &nbsp; need Nonbreaking Space Plugin
                    init_instance_callback: editor => {
                        if (_this.value) {
                            editor.setContent(_this.value)
                        }
                        _this.hasInit = true
                        editor.on('NodeChange Change KeyUp SetContent', () => {
                            this.hasChange = true
                            this.$emit('input', editor.getContent())
                        })
                    },
                    setup(editor) {
                        editor.on('FullscreenStateChanged', (e) => {
                            _this.fullscreen = e.state
                        })
                    }
                })
            },
            destroyTinymce() {
                if (window.tinymce.get(this.tinymceId)) {
                    window.tinymce.get(this.tinymceId).destroy()
                }
            },
            setContent(value) {
                window.tinymce.get(this.tinymceId).setContent(value)
            },
            getContent() {
                window.tinymce.get(this.tinymceId).getContent()
            },
            imageSuccessCBK(arr) {
                const _this = this
                arr.forEach(v => {
                    window.tinymce.get(_this.tinymceId).insertContent(`<img class="wscnph" src="${v.url}" >`)
                })
            }
        },
        destroyed() {
            this.destroyTinymce()
        }
    }
</script>

<style type="less" scoped>
    .tinymce-container {
        position: relative;
    }

    .tinymce-container >>> .mce-fullscreen {
        z-index: 10000;
    }

    .tinymce-textarea {
        visibility: hidden;
        z-index: -1;
    }

    .editor-custom-btn-container {
        position: absolute;
        right: 4px;
        top: -2px;
        z-index: 2005;
    }

    .fullscreen .editor-custom-btn-container {
        z-index: 10000;
        position: fixed;
    }

    .editor-upload-btn {
        display: inline-block;
    }
</style>
