package com.xunlei.downloadprovider.download.tasklist.list.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobad.feeds.NativeResponse;
import com.xunlei.downloadprovider.ad.common.d.c;
import com.xunlei.downloadprovider.ad.common.d.d;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;

// compiled from: TaskDownloadingAdCardViewHolder.java
final class ao implements OnClickListener {
    final /* synthetic */ NativeResponse a;
    final /* synthetic */ af b;

    ao(af afVar, NativeResponse nativeResponse) {
        this.b = afVar;
        this.a = nativeResponse;
    }

    public final void onClick(View view) {
        this.a.handleClick(af.e(this.b));
        a.b(h.a(af.a(this.b).a()), "baidu", "baidu", this.b.d(), BuildConfig.VERSION_NAME);
        com.xunlei.downloadprovider.ad.common.d.a.a aVar = new com.xunlei.downloadprovider.ad.common.d.a.a();
        n.a();
        aVar.b = d.a(n.a(af.a(this.b).a()), R.styleable.AppCompatTheme_buttonStyleSmall);
        n.a();
        aVar.c = n.a(af.a(this.b).a());
        aVar.a = 2;
        com.xunlei.downloadprovider.j.a.a().e().a(new c(aVar, new ap(this), new aq(this)));
    }
}
