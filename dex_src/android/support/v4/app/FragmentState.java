package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

final class FragmentState implements Parcelable {
    public static final Creator<FragmentState> CREATOR;
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final int mIndex;
    Fragment mInstance;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;

    public FragmentState(Fragment fragment) {
        this.mClassName = fragment.getClass().getName();
        this.mIndex = fragment.mIndex;
        this.mFromLayout = fragment.mFromLayout;
        this.mFragmentId = fragment.mFragmentId;
        this.mContainerId = fragment.mContainerId;
        this.mTag = fragment.mTag;
        this.mRetainInstance = fragment.mRetainInstance;
        this.mDetached = fragment.mDetached;
        this.mArguments = fragment.mArguments;
    }

    public FragmentState(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.mClassName = parcel.readString();
        this.mIndex = parcel.readInt();
        this.mFromLayout = parcel.readInt() != 0;
        this.mFragmentId = parcel.readInt();
        this.mContainerId = parcel.readInt();
        this.mTag = parcel.readString();
        if (parcel.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mRetainInstance = z;
        if (parcel.readInt() == 0) {
            z2 = false;
        }
        this.mDetached = z2;
        this.mArguments = parcel.readBundle();
        this.mSavedFragmentState = parcel.readBundle();
    }

    public final Fragment instantiate(FragmentHostCallback fragmentHostCallback, Fragment fragment) {
        if (this.mInstance != null) {
            return this.mInstance;
        }
        Context context = fragmentHostCallback.getContext();
        if (this.mArguments != null) {
            this.mArguments.setClassLoader(context.getClassLoader());
        }
        this.mInstance = Fragment.instantiate(context, this.mClassName, this.mArguments);
        if (this.mSavedFragmentState != null) {
            this.mSavedFragmentState.setClassLoader(context.getClassLoader());
            this.mInstance.mSavedFragmentState = this.mSavedFragmentState;
        }
        this.mInstance.setIndex(this.mIndex, fragment);
        this.mInstance.mFromLayout = this.mFromLayout;
        this.mInstance.mRestored = true;
        this.mInstance.mFragmentId = this.mFragmentId;
        this.mInstance.mContainerId = this.mContainerId;
        this.mInstance.mTag = this.mTag;
        this.mInstance.mRetainInstance = this.mRetainInstance;
        this.mInstance.mDetached = this.mDetached;
        this.mInstance.mFragmentManager = fragmentHostCallback.mFragmentManager;
        if (FragmentManagerImpl.DEBUG) {
            new StringBuilder("Instantiated fragment ").append(this.mInstance);
        }
        return this.mInstance;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(this.mClassName);
        parcel.writeInt(this.mIndex);
        parcel.writeInt(this.mFromLayout ? 1 : 0);
        parcel.writeInt(this.mFragmentId);
        parcel.writeInt(this.mContainerId);
        parcel.writeString(this.mTag);
        if (this.mRetainInstance) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!this.mDetached) {
            i3 = 0;
        }
        parcel.writeInt(i3);
        parcel.writeBundle(this.mArguments);
        parcel.writeBundle(this.mSavedFragmentState);
    }

    static {
        CREATOR = new Creator<FragmentState>() {
            public final FragmentState createFromParcel(Parcel parcel) {
                return new FragmentState(parcel);
            }

            public final FragmentState[] newArray(int i) {
                return new FragmentState[i];
            }
        };
    }
}
