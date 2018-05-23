## 一个开源项目，使用 rxjava + mvp + retrofit

### 开发进度
#### 每日一文
    1、默认显示当天的一则文章，当天文章的背景为红色
    2、下拉刷新一条新的文章，内容随机，最新的数据用蓝色背景标识
    2、之前刷新出来的文章，用灰色背景标识

### 技术点总结
###### 取消 RadioButton
	    android:button="@null"
###### 登录页的的登录协使用 `SpannableString`
		String text = "登录即代表阅读并同意服务条款";
	    int len = text.length();
	    SpannableString spannableString = new SpannableString(text);
	    mItemTv.setMovementMethod(LinkMovementMethod.getInstance());//必须设置否则无效
	    spannableString.setSpan(new ClickableSpan() {
	        @Override
	        public void onClick(final View widget) {
	            startActivity(new Intent(mActivity, ProtocolItemActivity.class));
	        }

	        @Override
	        public void updateDrawState(final TextPaint ds) {
	            super.updateDrawState(ds);
	            ds.setColor(Color.parseColor("#0061ff"));
	            ds.setUnderlineText(false);    //去除超链接的下划线
	        }
	    }, len - 4, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	    //改变选中文本的高亮颜色
		//mItemTv.setHighlightColor(Color.BLUE);
	    mItemTv.setText(spannableString);

