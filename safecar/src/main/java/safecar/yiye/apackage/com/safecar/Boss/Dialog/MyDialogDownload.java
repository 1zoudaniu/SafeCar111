package safecar.yiye.apackage.com.safecar.Boss.Dialog;


public class MyDialogDownload
	//TODO 确认下载页面弹出的对话框
//		extends Dialog
{
//	private ImageView loading_image;
//	private AnimationDrawable animationDrawable;
//	private TextView dialogDownloadTextContext;
//	private TextView dialohDownloadTextNo;
//	private TextView dialogDownloadTextYes;
//	private Context context;
//	public MyDialogDownload(Context context, int theme) {
//		super(context, theme);
//		this.context=context;
//	}
//	protected MyDialogDownload(Context context, boolean cancelable,
//							   OnCancelListener cancelListener) {
//		super(context, cancelable, cancelListener);
//	}
//	public MyDialogDownload(Context context) {
//		super(context);
//	}
//	Mydialog_interface listener;
//	String bookname;
//	public void init(String bookname,Mydialog_interface listener){
//		this.listener=listener;
//		this.bookname=bookname;
//		try {
//			initdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void initdate() {
//		String booknameInfo= String.format(context.getResources().getString(R.string.book_name),bookname );
//		dialogDownloadTextContext.setText(booknameInfo);
//	}
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		this.setContentView(R.layout.dialog_download);
//		initview();
//	}
//	public void initview() {
//		dialogDownloadTextContext = (TextView) findViewById(R.id.dialog_download_text_context);
//		dialohDownloadTextNo = (TextView) findViewById(R.id.dialoh_download_text_no);
//		dialogDownloadTextYes = (TextView) findViewById(R.id.dialog_download_text_yes);
//
//		dialohDownloadTextNo.setOnClickListener(listener.no());
//		dialogDownloadTextYes.setOnClickListener(listener.yes());
//		initdate();
//	}
}
